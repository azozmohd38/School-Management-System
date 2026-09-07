package entities;

import utils.HelperUtils;

public class HeadTeacher extends Teacher {

    private int teachersManaged;
    private boolean adminOfficeAccess;
    private String[] upcomingMeetingDates;
    private int meetingCount;

    public HeadTeacher(String id, String firstName, String lastName, String dateOfBirth,
                       String gender, String phoneNumber, String email, String address,
                       String nationalId, int age, boolean active, String subject,
                       int experienceYears, double salary, boolean formTeacher,
                       int teachersManaged, boolean adminOfficeAccess) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address,
                nationalId, age, active, subject, experienceYears, salary, formTeacher);
        setTeachersManaged(teachersManaged);
        setAdminOfficeAccess(adminOfficeAccess);
        upcomingMeetingDates = new String[30];
    }

    public int getTeachersManaged() {
        return teachersManaged;
    }

    public void setTeachersManaged(int teachersManaged) {
        if (teachersManaged < 0) {
            throw new IllegalArgumentException("Managed teacher count cannot be negative");
        }
        this.teachersManaged = teachersManaged;
    }

    public boolean hasAdminOfficeAccess() {
        return adminOfficeAccess;
    }

    public void setAdminOfficeAccess(boolean adminOfficeAccess) {
        this.adminOfficeAccess = adminOfficeAccess;
    }

    public void addManagedTeacher() {
        teachersManaged++;
    }

    public boolean scheduleMeeting(String meetingDate) {
        if (!HelperUtils.isValidText(meetingDate) || meetingCount >= upcomingMeetingDates.length) {
            return false;
        }
        for (int i = 0; i < meetingCount; i++) {
            if (upcomingMeetingDates[i].equalsIgnoreCase(meetingDate.trim())) {
                return false;
            }
        }
        upcomingMeetingDates[meetingCount++] = meetingDate.trim();
        return true;
    }

    public int getUpcomingCount() {
        return meetingCount;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Teachers Managed: " + teachersManaged);
        System.out.println("Admin Office Access: " + adminOfficeAccess);
        System.out.println("Upcoming Meetings: " + meetingCount);
    }

    @Override
    public void displaySummary() {
        System.out.println(getId() + " - " + getFullName() + " - Head Teacher");
    }
}
