package com.harsh.lessonswithai.Core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericDataDto<T> {
   private int responseCode = 200;
   private String responseMessage;
   private List<T> dataList;
   private T data;

   public ResponseEntity<GenericDataDto<T>> sendResponse(){
      return ResponseEntity.status(responseCode).body(this);
   }

   public void setError(int responseCode,String responseMessage){
      this.responseCode = responseCode;
      this.responseMessage = responseMessage;
   }

   public void setResponseData(int responseCode,String responseMessage,T data, List<T> dataList){
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

   public void setResponseData(int responseCode,String responseMessage){
      this.responseCode = responseCode;
      this.responseMessage = responseMessage;
   }

   public void setResponseData(int responseCode,String responseMessage, List<T> dataList){
      this.responseCode = responseCode;
      this.responseMessage = responseMessage;
      this.dataList = dataList;
   }
}

