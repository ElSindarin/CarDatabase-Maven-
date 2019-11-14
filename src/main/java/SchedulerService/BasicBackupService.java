package SchedulerService;

import cardatabase.CarList;

import javax.naming.InsufficientResourcesException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static ExportImportService.ExportService.exportToCSV;

public class BasicBackupService {


    public static void addSimpleBackupService (CarList carDatabase, String input) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                exportToCSV(carDatabase,input,"backupData");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InsufficientResourcesException e) {
                e.printStackTrace();
            }
        }, 30, 30, TimeUnit.SECONDS);
    }

    public static void addComplexBackupService (CarList carList, String input) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            int size = 10;
            for (int i = 0; i < size; i++) {
                Path path = Paths.get(input + "\\" + "backup" + i + ".csv");
                if (!path.toFile().exists()) {
                    try {
                        exportToCSV(carList, input, "backup" + i);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InsufficientResourcesException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            Path path = Paths.get(input + "\\" + "backup" + (size - 1)+ ".csv");
            if (path.toFile().exists()) {
                for (int i = 0; i < size - 1; i++) {
                    Path source = Paths.get(input + "\\" + "backup" + (i + 1) + ".csv");
                    Path dest = Paths.get(input + "\\" + "backup" + i + ".csv");
                    try {
                        Files.deleteIfExists(dest);
                        Files.copy(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Files.deleteIfExists(Paths.get(input + "\\" + "backup" + (size - 1) + ".csv"));
                    exportToCSV(carList, input, "backup" + (size - 1));
                } catch (IOException | InsufficientResourcesException e) {
                    e.printStackTrace();
                }
            }
        }, 10,10,TimeUnit.MINUTES);
    }
}
