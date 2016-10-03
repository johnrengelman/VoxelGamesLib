package me.MiniDigger.VoxelGamesLib.api.role;

import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.MiniDigger.VoxelGamesLib.api.exception.DuplicatePermissionDefinitionException;
import me.MiniDigger.VoxelGamesLib.api.exception.NoSuchRoleException;
import me.MiniDigger.VoxelGamesLib.api.handler.Handler;

/**
 * Handles all roles for this server. TODO javadoc for RoleHandler
 */
@Singleton
public class RoleHandler implements Handler {

    private List<Permission> knownPermissions = new ArrayList<>();

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        knownPermissions.clear();
    }

    /**
     * Tries to register a new permission object. will return existing one instead of duplicating.
     *
     * @param perm the perm string
     * @param role the role name
     * @return the permission object that belongs to the given input
     * @throws NoSuchRoleException when a role is not registered
     */
    public Permission registerPermission(String perm, String role) {
        Optional<Role> r = getRole(role);
        if (!r.isPresent()) {
            throw new NoSuchRoleException(role);
        }

        Optional<Permission> opt = getPermission(perm);
        if (opt.isPresent()) {
            if (!opt.get().getRole().getName().equalsIgnoreCase(role)) {
                throw new DuplicatePermissionDefinitionException(opt.get(), role);
            }
        }

        Permission p = new Permission(perm, r.get());
        knownPermissions.add(p);
        return p;
    }

    /**
     * Searches for a permission object with that permission string
     *
     * @param perm the permission string to search for
     * @return the optional search result
     */
    public Optional<Permission> getPermission(String perm) {
        return knownPermissions.stream().filter(p -> p.getString().equalsIgnoreCase(perm)).findAny();
    }

    /**
     * Searches for a role with that name
     *
     * @param role the name of the role to search for
     * @return the optional search result
     */
    public Optional<Role> getRole(String role) {
        try {
            return Optional.of(Role.valueOf(role));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
