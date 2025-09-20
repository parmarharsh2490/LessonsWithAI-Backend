package com.harsh.lessonswithai.Utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;

@Getter
@Setter
public class GenericDataDto<T> {
   private int responseCode;
   private String responseMessage;
   private ArrayList<T> dataList;
   private T data;

   public ResponseEntity<GenericDataDto<T>> sendResponse(){
      return ResponseEntity.status(responseCode).body(this);
   }

   public void setError(int responseCode,String responseMessage){
      this.responseCode = responseCode;
      this.responseMessage = responseMessage;
   }

   public void setResponseData(int responseCode,String responseMessage,T data, ArrayList<T> dataList){
      this.responseCode = responseCode;
      this.responseMessage = responseMessage;
      this.data = data;
      this.dataList = dataList;
   }

   public void setResponseData(int responseCode,String responseMessage,T data){
      this.responseCode = responseCode;
      this.responseMessage = responseMessage;
      this.data = data;
   }

   public void setResponseData(int responseCode,String responseMessage, ArrayList<T> dataList){
      this.responseCode = responseCode;
      this.responseMessage = responseMessage;
      this.dataList = dataList;
   }
}

