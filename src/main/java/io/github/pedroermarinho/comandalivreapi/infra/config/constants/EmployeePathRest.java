package io.github.pedroermarinho.comandalivreapi.infra.config.constants;

public class EmployeePathRest {

    private EmployeePathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EMPLOYEE = "/employee";
    public static final String EMPLOYEE_REGISTER = EMPLOYEE;
    public static final String EMPLOYEE_SEARCH = EMPLOYEE;
    public static final String EMPLOYEE_UPDATE = EMPLOYEE;
    public static final String EMPLOYEE_STATUS = EMPLOYEE + "/disable";
}
