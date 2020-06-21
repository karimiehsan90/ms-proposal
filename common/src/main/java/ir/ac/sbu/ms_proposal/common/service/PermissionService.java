package ir.ac.sbu.ms_proposal.common.service;

import ir.ac.sbu.ms_proposal.common.conf.Auth;
import ir.ac.sbu.ms_proposal.common.entity.Permission;
import ir.ac.sbu.ms_proposal.common.request.PermissionRequest;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import org.springframework.stereotype.Component;

@Component
public class PermissionService {
    private final ResponseParser parser;

    public PermissionService(ResponseParser parser) {
        this.parser = parser;
    }

    public boolean hasPermission(String token, Permission permission, Auth auth) {
        String hasPermissionResponse = new PermissionRequest(String
                .format("http://%s:%s/permission/get", auth.getHost(), auth.getPort()),
                permission, token).sendRequest();
        ActionResult<Boolean> hasPermissionResult = parser.parseResponse(hasPermissionResponse, Boolean.class);
        return hasPermissionResult.getData();
    }
}
