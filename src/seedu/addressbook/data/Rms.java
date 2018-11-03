package seedu.addressbook.data;

import java.util.HashSet;
import java.util.Set;

import seedu.addressbook.data.employee.Attendance;
import seedu.addressbook.data.employee.Employee;
import seedu.addressbook.data.employee.ReadOnlyEmployee;
import seedu.addressbook.data.employee.UniqueAttendanceList;
import seedu.addressbook.data.employee.UniqueEmployeeList;
import seedu.addressbook.data.employee.UniqueEmployeeList.DuplicateEmployeeException;
import seedu.addressbook.data.employee.UniqueEmployeeList.EmployeeNotFoundException;
import seedu.addressbook.data.member.Member;
import seedu.addressbook.data.member.ReadOnlyMember;
import seedu.addressbook.data.member.UniqueMemberList;
import seedu.addressbook.data.member.UniqueMemberList.DuplicateMemberException;
import seedu.addressbook.data.member.UniqueMemberList.MemberNotFoundException;
import seedu.addressbook.data.menu.Menu;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.menu.Type;
import seedu.addressbook.data.menu.UniqueMenuList;
import seedu.addressbook.data.menu.UniqueMenuList.DuplicateMenuException;
import seedu.addressbook.data.menu.UniqueMenuList.MenuNotFoundException;
import seedu.addressbook.data.order.Order;
import seedu.addressbook.data.order.ReadOnlyOrder;
import seedu.addressbook.data.order.UniqueOrderList;
import seedu.addressbook.data.order.UniqueOrderList.DuplicateOrderException;
import seedu.addressbook.data.order.UniqueOrderList.OrderNotFoundException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

/**
 * Represents the entire address book. Contains the data of the address book.
 */
public class Rms {

    private static Set<Type> typeSet = new HashSet<>();

    private final UniquePersonList allPersons;
    private final UniqueEmployeeList allEmployees;
    private final UniqueMemberList allMembers;
    private final UniqueMenuList allFoodItems;
    private final UniqueOrderList allOrders;
    private final UniqueAttendanceList allAttendance;

    private Order draftOrder = new Order();

    /**
     * Creates an empty address book.
     */
    public Rms() {
        allPersons = new UniquePersonList();
        allEmployees = new UniqueEmployeeList();
        allMembers = new UniqueMemberList();
        allFoodItems = new UniqueMenuList();
        allOrders = new UniqueOrderList();
        allAttendance = new UniqueAttendanceList();
    }

    /**
     * Constructs an address book with the given data.
     *
     * @param persons external changes to this will not affect this address book
     */
    public Rms(UniquePersonList persons,
               UniqueMenuList menus,
               UniqueEmployeeList employees,
               UniqueOrderList orders,
               UniqueMemberList members,
               UniqueAttendanceList attendances) {
        this.allPersons = new UniquePersonList(persons);
        this.allEmployees = new UniqueEmployeeList(employees);
        this.allMembers = new UniqueMemberList(members);
        this.allFoodItems = new UniqueMenuList(menus);
        this.allOrders = new UniqueOrderList(orders);
        this.allAttendance = new UniqueAttendanceList(attendances);
    }

    public static Rms empty() {
        return new Rms();
    }

    /**
     * Adds a person to the address book.
     *
     * @throws Exception if an equivalent person already exists.
     */
    public void addPerson(Person toAdd) throws Exception {
        allPersons.add(toAdd);
    }

    /**
     * Adds an employee to the Rms.
     *
     * @throws DuplicateEmployeeException if an equivalent employee already exists.
     */
    public void addEmployee(Employee toAdd) throws DuplicateEmployeeException {
        allEmployees.add(toAdd);
    }

    /**
     * Adds an attendance list with the specified employee to the Rms.
     */
    public void addAttendance(Attendance toAdd) {
        allAttendance.add(toAdd);
    }

    /**
     * Gets index of the specified Attendance object.
     */
    public int findAttendanceIndex(String toFind) {
        return allAttendance.getAttendanceIndex(toFind);
    }


    /**
     * Gets index of the specified Attendance object.
     */
    public Attendance findAttendance(int toFind) {
        return allAttendance.getAttendance(toFind);
    }

    /**
     * Adds an attendance list with the specified employee to the Rms.
     */
    public void updateAttendance(Attendance oldAttendance, Attendance newAttendance) {
        allAttendance.setAttendance(oldAttendance, newAttendance); }

    /**
     * Adds a member to the address book.
     *
     * @throws DuplicateMemberException if an equivalent member already exists.
     */

    public void addMember(Member toAdd) throws DuplicateMemberException {
        allMembers.add(toAdd);
    }

    /**
     * Adds a menu item to the menu list.
     *
     * @throws DuplicateMenuException if an equivalent menu item already exists.
     */
    public void addMenu(Menu toAdd) throws DuplicateMenuException {
        allFoodItems.add(toAdd);
    }

    /**
     * Adds an order to the order list.
     *
     * @throws DuplicateOrderException if an equivalent person already exists.
     */
    public void addOrder(Order toAdd) throws DuplicateOrderException {
        allOrders.add(toAdd);
    }

    /**
     * Checks if an equivalent person exists in the address book.
     */
    public boolean containsPerson(ReadOnlyPerson key) {
        return allPersons.contains(key);
    }

    /**
     * Checks if an equivalent menu item exists in the address book.
     */
    public boolean containsMenus(ReadOnlyMenus key) {
        return allFoodItems.contains(key);
    }


    /**
     * Checks if an equivalent order exists in the order list.
     */
    public boolean containsOrder(ReadOnlyOrder key) {
        return allOrders.contains(key);
    }

    /**
     * Checks if an equivalent member exists in the address book.
     */
    public boolean containsMember(ReadOnlyMember key) {
        return allMembers.contains(key);
    }


    /**
     * Checks if an equivalent employee exists in the Rms.
     */
    public boolean containsEmployee(ReadOnlyEmployee key) {
        return allEmployees.containsDuplicate(key);
    }


    /**
     * Removes the equivalent person from the address book.
     *
     * @throws Exception if no such Person could be found.
     */
    public void removePerson(ReadOnlyPerson toRemove) throws Exception {
        allPersons.remove(toRemove);
    }

    /**
     * Removes the equivalent menu item from the menu.
     *
     * @throws MenuNotFoundException if no such Order could be found.
     */
    public void removeMenuItem(ReadOnlyMenus toRemove) throws MenuNotFoundException {
        allFoodItems.remove(toRemove);
    }

    /**
     * Removes the equivalent order from the order list.
     *
     * @throws OrderNotFoundException if no such Order could be found.
     */
    public void removeOrder(ReadOnlyOrder toRemove) throws OrderNotFoundException {
        allOrders.remove(toRemove);
    }

    /**
     * Removes the equivalent member from the address book.
     *
     * @throws MemberNotFoundException if no such Member could be found.
     */
    public void removeMember(ReadOnlyMember toRemove) throws MemberNotFoundException {
        allMembers.remove(toRemove);
    }

    /**
     * Removes the equivalent employee from the Rms.
     *
     * @throws EmployeeNotFoundException if no such Employee could be found.
     */
    public void removeEmployee(ReadOnlyEmployee toRemove) throws EmployeeNotFoundException {
        allEmployees.remove(toRemove);
    }

    /**
     * Removes an attendance list with the specified employee from the Rms.
     */
    public void removeAttendance(Attendance toRemove) {
        allAttendance.remove(toRemove);
    }

    /**
     * Edits the equivalent employee from Rms
     *
     * @throws EmployeeNotFoundException if no such Employee could be found.
     */
    public void editEmployee(ReadOnlyEmployee toRemove, Employee toReplace) throws EmployeeNotFoundException {
        allEmployees.edit(toRemove, toReplace);
    }

    /**
     * Clears all persons from the address book.
     */
    public void clear() {
        allPersons.clear();
    }

    /**
     * Clears all members from the address book.
     */
    public void clearMembers() {
        allMembers.clear();
    }

    /**
     * Clears all employees from the Rms.
     */
    public void clearEmployee() {
        allEmployees.clear();
    }

    /**
     * Clears all menu items from the menu.
     */
    public void clearMenu() {
        allFoodItems.clear();
    }

    /**
     * Clears all orders from the order list.
     */
    public void clearOrderList() {
        allOrders.clear();
    }

    /**
     * Defensively copied UniquePersonList of all persons in the address book at the time of the call.
     */
    public UniquePersonList getAllPersons() {
        return new UniquePersonList(allPersons);
    }

    /**
     * Defensively copied UniqueMemberList of all members in the member list at the time of the call.
     */
    public UniqueMemberList getAllMembers() {
        return new UniqueMemberList(allMembers);
    }

    /**
     * Defensively copied UniqueEmployeeList of all employees in the employee list at the time of the call.
     */
    public UniqueEmployeeList getAllEmployees() {
        return new UniqueEmployeeList(allEmployees);
    }


    /**
     * Defensively copied UniqueEmployeeList of all employees in the employee list at the time of the call.
     */
    public UniqueAttendanceList getAllAttendance() {
        return new UniqueAttendanceList(allAttendance);
    }

    /**
     * Defensively copied UniqueMenuList of all menu items in the menu at the time of the call.
     */
    public UniqueMenuList getAllMenus() {
        return new UniqueMenuList(allFoodItems);
    }

    /**
     * Defensively copied UniqueOrderList of all orders in the employee list at the time of the call.
     */
    public UniqueOrderList getAllOrders() {
        return new UniqueOrderList(allOrders);
    }

    public ReadOnlyOrder getDraftOrder() {
        return draftOrder;
    }

    public void editDraftOrderCustomer(ReadOnlyMember customer) {
        draftOrder.setCustomer(customer);
    }

    /**
     * Adjust the dish and its quantity in the draft order to add, remove or edit dish items in the draft.
     */
    public void editDraftOrderDishItem(ReadOnlyMenus dish, int quantity) {
        draftOrder.changeDishQuantity(dish, quantity);
    }

    public void clearDraftOrder() {
        draftOrder = new Order();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rms // instanceof handles nulls
                && this.allPersons.equals(((Rms) other).allPersons));
    }

    @Override
    public int hashCode() {
        return allPersons.hashCode();
    }
}

