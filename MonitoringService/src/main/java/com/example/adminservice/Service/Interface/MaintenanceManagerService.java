package com.example.adminservice.Service.Interface;

import java.io.Serializable;
import java.util.List;

public interface MaintenanceManagerService {
    String underMaintenance(long scooterId);

    String finalizeMaintenance(long maintenance);

    List<Serializable> getReport(String filter, Boolean pauseTime);
}
