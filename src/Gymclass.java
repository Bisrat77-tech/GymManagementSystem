public class Gymclass {
    private int classId;
    private String className;
    private String trainerName;
    private String schedule;
    private int maxCapacity;
    private int enrolledCount;

    public Gymclass(int classId,String className, String trainerName,
                    String schedule, int maxCapacity){
        this.classId = classId;
        this.className = className;
        this.trainerName = trainerName;
        this.schedule = schedule;
        this.maxCapacity = maxCapacity;
        this.enrolledCount = 0;
    }

    public int    getClassId()       { return classId;       }
    public String getClassName()     { return className;     }
    public String getTrainerName()   { return trainerName;   }
    public String getSchedule()      { return schedule;      }
    public int    getMaxCapacity()   { return maxCapacity;   }
    public int    getEnrolledCount() { return enrolledCount; }
    public int    getSlotsLeft()     { return maxCapacity - enrolledCount; }

    public void setMaxCapacity(int cap) {
        if (cap < 1) throw new IllegalArgumentException("Capacity must be at least 1.");
        this.maxCapacity = cap;
    }

    public void setSchedule(String schedule) {
        if (schedule == null || schedule.trim().isEmpty()) {
            throw new IllegalArgumentException("Schedule cannot be empty.");
        }
        this.schedule = schedule.trim();
    }
    public boolean enroll(){
        if (enrolledCount >= maxCapacity)return false;
        enrolledCount++;
        return true;
    }

    public void describe(){
        System.out.printf("[%d] %-18s | Trainer: %-15s | %s | Slots: %d/%d%n",
                classId, className, trainerName, schedule, enrolledCount, maxCapacity);
    }
    public void describe(boolean verbose) {
        if (!verbose) { describe(); return; }
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.printf("│ CLASS DETAILS                           │%n");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.printf("│ Class ID   : %-27d│%n", classId);
        System.out.printf("│ Name       : %-27s│%n", className);
        System.out.printf("│ Trainer    : %-27s│%n", trainerName);
        System.out.printf("│ Schedule   : %-27s│%n", schedule);
        System.out.printf("│ Enrolled   : %d / %-23d│%n", enrolledCount, maxCapacity);
        System.out.printf("│ Slots Left : %-27d│%n", getSlotsLeft());
        System.out.println("└─────────────────────────────────────────┘");
    }
    public void describe(String label){
        System.out.printf("[%s] Class #%d: %s with %s (%s) -- %d%d enrolled%n",
                label, classId, className, trainerName, schedule,
                enrolledCount, maxCapacity);
    }
    @Override
    public String toString(){
        return String.format("GymClass{id=%d, name='%s', trainer = '%s', schedule = '%s', enrolled=%d/%d}",
                classId, className, trainerName, schedule
        ,enrolledCount ,maxCapacity);
    }

}
