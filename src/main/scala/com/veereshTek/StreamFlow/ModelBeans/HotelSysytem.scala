package com.veereshTek.StreamFlow.ModelBeans


case class Room(roomNumber :Int,floor:Int,SuiteType :String,Specialization :String)

case class Hotel(room: Room,availability:Boolean,date:String,amount:BigDecimal,customer :Customer)
case class Customer(Id:Int,firstName :String,lastName:String,age:Int,proofSubmitted:String)

