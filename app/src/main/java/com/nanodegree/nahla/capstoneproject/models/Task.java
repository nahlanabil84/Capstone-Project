package com.nanodegree.nahla.capstoneproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Task implements Parcelable {

    int taskId;
    boolean isDone;
    String taskTitle;
    String taskType;
    int taskPriority;
    String taskDate;
    String taskTime;
    boolean isLocationSet;
    float taskLocationLat;
    float taskLocationLng;
    String taskPhone;
    ArrayList<String> subTasks;
    String taskNote;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public boolean isLocationSet() {
        return isLocationSet;
    }

    public void setLocationSet(boolean locationSet) {
        isLocationSet = locationSet;
    }

    public float getTaskLocationLat() {
        return taskLocationLat;
    }

    public void setTaskLocationLat(float taskLocationLat) {
        this.taskLocationLat = taskLocationLat;
    }

    public float getTaskLocationLng() {
        return taskLocationLng;
    }

    public void setTaskLocationLng(float taskLocationLng) {
        this.taskLocationLng = taskLocationLng;
    }

    public String getTaskPhone() {
        return taskPhone;
    }

    public void setTaskPhone(String taskPhone) {
        this.taskPhone = taskPhone;
    }

    public ArrayList<String> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<String> subTasks) {
        this.subTasks = subTasks;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.taskId);
        dest.writeByte(this.isDone ? (byte) 1 : (byte) 0);
        dest.writeString(this.taskTitle);
        dest.writeString(this.taskType);
        dest.writeInt(this.taskPriority);
        dest.writeString(this.taskDate);
        dest.writeString(this.taskTime);
        dest.writeByte(this.isLocationSet ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.taskLocationLat);
        dest.writeFloat(this.taskLocationLng);
        dest.writeString(this.taskPhone);
        dest.writeStringList(this.subTasks);
        dest.writeString(this.taskNote);
    }

    public Task() {
    }

    protected Task(Parcel in) {
        this.taskId = in.readInt();
        this.isDone = in.readByte() != 0;
        this.taskTitle = in.readString();
        this.taskType = in.readString();
        this.taskPriority = in.readInt();
        this.taskDate = in.readString();
        this.taskTime = in.readString();
        this.isLocationSet = in.readByte() != 0;
        this.taskLocationLat = in.readFloat();
        this.taskLocationLng = in.readFloat();
        this.taskPhone = in.readString();
        this.subTasks = in.createStringArrayList();
        this.taskNote = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
