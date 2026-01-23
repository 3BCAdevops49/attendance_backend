package com.example.attendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.attendance.entity.Attendance;
import com.example.attendance.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository repo;

    public AttendanceService(AttendanceRepository repo) {
        this.repo = repo;
    }

    public Attendance saveAttendance(Attendance a) {
        double percent = (a.getAttendedClasses() * 100.0) / a.getTotalClasses();
        a.setPercentage(percent);

        if (percent < 75)
            a.setStatus("AT RISK");
        else
            a.setStatus("SAFE");

        return repo.save(a);
    }

    public Attendance updateAttendance(Long id, Attendance a) {
        Attendance existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        
        existing.setStudentName(a.getStudentName());
        existing.setTotalClasses(a.getTotalClasses());
        existing.setAttendedClasses(a.getAttendedClasses());
        
        double percent = (existing.getAttendedClasses() * 100.0) / existing.getTotalClasses();
        existing.setPercentage(percent);

        if (percent < 75)
            existing.setStatus("AT RISK");
        else
            existing.setStatus("SAFE");

        return repo.save(existing);
    }

    public List<Attendance> getAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
