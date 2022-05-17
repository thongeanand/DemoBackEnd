package com.moneyware.application.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
public class FileDB {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Lob
  private byte[] data;

  @Column(name = "filename")
  private String fileName;

  @Column(name = "filesize")
  private long fileSize;

  @Column(name = "timestamp")
  private String timeStamp;

  @Column
  private int customerId;

  @Column
  private String documentType;

  @Column
  private String status;

  public FileDB(String fileName, long fileSize, String timeStamp, int customerId, String documentType, String status, byte[] data) {
    this.fileName = fileName;
    this.fileSize = fileSize;
    this.timeStamp = timeStamp;
    this.customerId = customerId;
    this.documentType = documentType;
    this.status = status;
    this.data = data;
  }
}
