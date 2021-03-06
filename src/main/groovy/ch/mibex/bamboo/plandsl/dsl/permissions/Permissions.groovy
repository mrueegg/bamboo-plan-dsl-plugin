package ch.mibex.bamboo.plandsl.dsl.permissions

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=false, excludes=['metaClass'])
@ToString(includeFields=true)
class Permissions extends BambooObject {
    private Map<String, Set<PermissionTypes.PermissionType>> user = [:]
    private Map<String, Set<PermissionTypes.PermissionType>> group = [:]
    private Map<OtherUserType, Set<PermissionTypes.PermissionType>> other = [:]

    protected Permissions(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected Permissions() {}

    static enum OtherUserType {
        LOGGED_IN_USERS('ROLE_USER'),
        ANONYMOUS_USERS('ROLE_ANONYMOUS')

        private String internalName

        OtherUserType(String internalName) {
            this.internalName = internalName
        }
    }

    /**
     * Permissions for a specific user.
     *
     * @param params A collection of properties. Currently only "name" is supported.
     */
    void user(Map<String, String> params,
              @DelegatesTo(value = PermissionTypes, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        user(params['name'], closure)
    }

    /**
     * Permissions for a specific user.
     *
     * @param name Name of the user
     */
    void user(String name,
              @DelegatesTo(value = PermissionTypes, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def permissionTypes = new PermissionTypes(bambooFacade, { permissions -> user[name] = permissions })
        DslScriptHelper.execute(closure, permissionTypes)
    }

    /**
     * Permissions for a specific group.
     *
     * @param params A collection of properties. Currently only "name" is supported.
     */
    void group(Map<String, String> params,
               @DelegatesTo(value = PermissionTypes, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        group(params['name'], closure)
    }

    /**
     * Permissions for a specific group.
     *
     * @param name Name of the group
     */
    void group(String name,
               @DelegatesTo(value = PermissionTypes, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def permissionTypes = new PermissionTypes(bambooFacade, { permissions -> group[name] = permissions })
        DslScriptHelper.execute(closure, permissionTypes)
    }

    /**
     * Permissions for other user types.
     *
     * @param params A collection of properties. Currently only "type" is supported.
     */
    void other(Map<String, Object> params,
               @DelegatesTo(value = PermissionTypes, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        other(params['type'] as OtherUserType, closure)
    }

    /**
     * Permissions for another user type.
     *
     * @param otherUserType user type
     */
    void other(OtherUserType otherUserType,
               @DelegatesTo(value = PermissionTypes, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def permissionTypes = new PermissionTypes(bambooFacade, { permissions -> other[otherUserType] = permissions })
        DslScriptHelper.execute(closure, permissionTypes)
    }
}
