package seedu.addressbook.data.menu;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Menu type in the Menu list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTypeName(String)}
 */
public class Type {

    public static final String EXAMPLE = "main";
    public static final String MESSAGE_TYPE_CONSTRAINTS = "Item Type should only be one of the few Category:"
                                                           + "\n" + "main"
                                                           + "\n" + "sides"
                                                           + "\n" + "beverage"
                                                           + "\n" + "dessert"
                                                           + "\n" + "others"
                                                           + "\n" + "set meal";
    public static final String TYPE_VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String value;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Type(String name) throws IllegalValueException {
        name = name.trim();
        if (!isValidTypeName(name)) {
            throw new IllegalValueException(MESSAGE_TYPE_CONSTRAINTS);
        }
        this.value = name;
    }

    /**
     * Returns true if a given string is a valid dish type.
     */
    public static boolean isValidTypeName(String test) {
        return (test.equals("main")
                || test.equals("sides")
                || test.equals("beverage")
                || test.equals("dessert")
                || test.equals("others")
                || test.equals("set meal"))
                && test.matches(TYPE_VALIDATION_REGEX);


    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Type // instanceof handles nulls
                && this.value.equals(((Type) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
