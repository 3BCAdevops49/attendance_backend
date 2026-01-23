package com.example.attendance.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.attendance.entity.Attendance;
import com.example.attendance.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Attendance add(@RequestBody Attendance a) {
        return service.saveAttendance(a);
    }

    // READ
    @GetMapping
    public List<Attendance> getAll() {
        return service.getAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Attendance update(@PathVariable Long id, @RequestBody Attendance a) {
        return service.updateAttendance(id, a);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Record Deleted";
    }
}
