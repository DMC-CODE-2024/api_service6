package app.apiservice.features.trc.model;

public class UpdateTACStatusModel {

    private String tac;
    private String approvedBy;
    private String action;

    public String getTac() {
        return tac;
    }

    public UpdateTACStatusModel setTac(String tac) {
        this.tac = tac;
        return this;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public UpdateTACStatusModel setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
        return this;
    }

    public String getAction() {
        return action;
    }

    public UpdateTACStatusModel setAction(String action) {
        this.action = action;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateTACStatusModel{");
        sb.append("tac='").append(tac).append('\'');
        sb.append(", approvedBy='").append(approvedBy).append('\'');
        sb.append(", action='").append(action).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
