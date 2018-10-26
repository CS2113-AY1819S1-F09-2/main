package seedu.addressbook.data.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AsciiTable {
    private String[] headings;
    private List<List<String>> data;
    private int noOfColumns;
    private int[] columnWidths;

    private char colBorder;
    private char rowBorder;
    private char rowHBorder;
    private String prePad;
    private String postPad;

    public AsciiTable(String[] headings) {
        if (headings == null)
            throw new IllegalArgumentException("Headings is null.");
        if (headings.length == 0)
            throw new IllegalArgumentException("No headings.");

        this.colBorder = '|';
        this.rowBorder = '-';
        this.rowHBorder = '=';
        this.prePad = " ";
        this.postPad = " ";

        this.noOfColumns = headings.length;
        this.columnWidths = new int[noOfColumns];
        this.headings = headings;
        this.data = new ArrayList<>();
        for (int i=0; i<noOfColumns; i++) {
            this.columnWidths[i] = headings[i].length();
        }
    }

    public void addRow(String[] rowData) {
        if (rowData.length != this.noOfColumns)
            return;

        data.add(Arrays.asList(rowData));
        for (int i=0; i<this.noOfColumns; i++) {
            if (columnWidths[i] < rowData[i].length())
                columnWidths[i] = rowData[i].length();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(createRowBorder(true));

        for (int i=0; i<noOfColumns; i++) {
            String cellString = headings[i];
            sb.append(padCell(cellString, columnWidths[i], ' ', i==0, i==noOfColumns-1));
        }
        sb.append(createRowBorder(true));

        for (int i=0; i<data.size(); i++) {
            for (int j=0; j<noOfColumns; j++) {
                String cellString = data.get(i).get(j);
                sb.append(padCell(cellString, columnWidths[j], ' ', j==0, j==noOfColumns-1));
            }
            sb.append(createRowBorder(false));
        }
        sb.append("\n");
        return sb.toString();
    }

    private String createRowBorder(boolean heading) {
        int tableWidth = calculateTableWidth();
        StringBuffer outputBuffer = new StringBuffer(tableWidth);
        for (int i = 0; i < tableWidth; i++){
            if (heading)
                outputBuffer.append(rowHBorder);
            else
                outputBuffer.append(rowBorder);
        }
        return "\n" + outputBuffer.toString() + "\n";
    }

    private int calculateTableWidth() {
        int width = 0;
        width += 1;
        for (int columnWidth : columnWidths) {
            width += columnWidth;
            width += 1;
            width += prePad.length();
            width += postPad.length();
        }

        return width;
    }

    private String padCell(String in, int width, char pad, boolean first, boolean last) {
        int cellSize = in.length();
        int padSize = width - cellSize;
        StringBuffer outputBuffer = new StringBuffer(padSize);
        for (int i = 0; i < padSize; i++){
            outputBuffer.append(pad);
        }


        return ((first) ? colBorder : "") + this.prePad + in + outputBuffer.toString() + this.postPad + colBorder;
    }

    private void setPrePad(String prePad) {
        this.prePad = prePad;
    }

    private void setPostPad(String postPad) {
        this.postPad = postPad;
    }

    private void setRowBorder(char rowBorder) {
        this.rowBorder = rowBorder;
    }

    private void setColBorder(char colBorder) {
        this.colBorder = colBorder;
    }

    private void setRowHBorder(char rowHBorder) {
        this.rowHBorder = rowHBorder;
    }
}