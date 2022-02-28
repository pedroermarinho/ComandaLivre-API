package io.github.pedroermarinho.comandalivreapi.infra.config.constants;

public class EmployeeAtOrganizationPathRest {

    private EmployeeAtOrganizationPathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EMPLOYEEATORGANIZATION = "/employeeatorganization";
    public static final String EMPLOYEEATORGANIZATION_REGISTER = EMPLOYEEATORGANIZATION;
    public static final String EMPLOYEEATORGANIZATION_SEARCH = EMPLOYEEATORGANIZATION;
    public static final String EMPLOYEEATORGANIZATION_UPDATE = EMPLOYEEATORGANIZATION;
    public static final String EMPLOYEEATORGANIZATION_STATUS = EMPLOYEEATORGANIZATION + "/disable";
}
