package ua.goit.database;

import ua.goit.database.dto.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting H2 Database Demo Application ===");

        // Database schema is initialized and seeded automatically via Flyway migrations.


        // 3. Query data
        System.out.println("\n--- Step 3: Executing Queries via DatabaseQueryService ---");
        DatabaseQueryService queryService = new DatabaseQueryService();

        System.out.println("\nClients with Maximum Projects:");
        List<MaxProjectCountClient> maxProjectsClients = queryService.findMaxProjectsClient();
        maxProjectsClients.forEach(System.out::println);

        System.out.println("\nWorkers with Maximum Salary:");
        List<MaxSalaryWorker> maxSalaryWorkers = queryService.findMaxSalaryWorker();
        maxSalaryWorkers.forEach(System.out::println);

        System.out.println("\nLongest Projects:");
        List<LongestProject> longestProjects = queryService.findLongestProject();
        longestProjects.forEach(System.out::println);

        System.out.println("\nYoungest and Eldest Workers:");
        List<YoungestEldestWorker> youngestEldestWorkers = queryService.findYoungestEldestWorkers();
        youngestEldestWorkers.forEach(System.out::println);

        System.out.println("\nProject Prices:");
        List<ProjectPrice> projectPrices = queryService.printProjectPrices();
        projectPrices.forEach(System.out::println);

        Database.getInstance().close();
        System.out.println("\n=== Demo Application Finished ===");
    }
}
