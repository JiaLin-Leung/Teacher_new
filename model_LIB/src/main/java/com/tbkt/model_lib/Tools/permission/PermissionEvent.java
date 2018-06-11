package com.tbkt.model_lib.Tools.permission;

public class PermissionEvent {
    private int eventId;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    private String permission;

    public PermissionEvent(int eventId,String permission) {
        this.eventId = eventId;
        this.permission = permission;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

}
