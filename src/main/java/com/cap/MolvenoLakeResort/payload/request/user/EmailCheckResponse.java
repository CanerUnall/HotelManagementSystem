package com.cap.MolvenoLakeResort.payload.request.user;

public class EmailCheckResponse {
        private boolean exists;

    public EmailCheckResponse(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
