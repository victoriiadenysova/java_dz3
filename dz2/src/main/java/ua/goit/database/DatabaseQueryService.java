package ua.goit.database;

import ua.goit.database.dto.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private String readSqlFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read SQL script: " + filePath, e);
        }
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sql = readSqlFile("sql/find_max_projects_client.sql");
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                int projectCount = rs.getInt("project_count");
                result.add(new MaxProjectCountClient(name, projectCount));
            }
        } catch (SQLException e) {
            System.err.println("Error executing findMaxProjectsClient: " + e.getMessage());
        }
        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> result = new ArrayList<>();
        String sql = readSqlFile("sql/find_max_salary_worker.sql");
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                int salary = rs.getInt("salary");
                result.add(new MaxSalaryWorker(name, salary));
            }
        } catch (SQLException e) {
            System.err.println("Error executing findMaxSalaryWorker: " + e.getMessage());
        }
        return result;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> result = new ArrayList<>();
        String sql = readSqlFile("sql/find_longest_project.sql");
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                int monthCount = rs.getInt("month_count");
                result.add(new LongestProject(name, monthCount));
            }
        } catch (SQLException e) {
            System.err.println("Error executing findLongestProject: " + e.getMessage());
        }
        return result;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        List<YoungestEldestWorker> result = new ArrayList<>();
        String sql = readSqlFile("sql/find_youngest_eldest_workers.sql");
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String type = rs.getString("type");
                String name = rs.getString("name");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                result.add(new YoungestEldestWorker(type, name, birthday));
            }
        } catch (SQLException e) {
            System.err.println("Error executing findYoungestEldestWorkers: " + e.getMessage());
        }
        return result;
    }

    public List<ProjectPrice> printProjectPrices() {
        List<ProjectPrice> result = new ArrayList<>();
        String sql = readSqlFile("sql/print_project_prices.sql");
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                result.add(new ProjectPrice(name, price));
            }
        } catch (SQLException e) {
            System.err.println("Error executing printProjectPrices: " + e.getMessage());
        }
        return result;
    }
}
