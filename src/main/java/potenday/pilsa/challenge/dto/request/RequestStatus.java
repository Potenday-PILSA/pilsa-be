package potenday.pilsa.challenge.dto.request;

import potenday.pilsa.challenge.domain.Status;

import java.util.Collections;
import java.util.List;

public enum RequestStatus {
    ING(Collections.singletonList(Status.ING)),
    EXPECTED(Collections.singletonList(Status.EXPECTED)),
    END(List.of(Status.FAIL, Status.SUCCESS));

    private final List<Status> mappedStatuses;

    RequestStatus(List<Status> mappedStatuses) {
        this.mappedStatuses = mappedStatuses;
    }

    public List<Status> getMappedStatuses() {
        return this.mappedStatuses;
    }
}
