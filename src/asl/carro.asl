!carro_java.

+!carro_java
  <- cartago.new_obj("Carro",[],Id);
     
     cartago.invoke_obj("java.lang.Class",forName("Carro"),Class);
     println(Class).
	 
	 //EXEMPLOS
	 //cartago.invoke_obj(Id,inc);
     //cartago.invoke_obj(Id,getValue,Res);
     //println(Res);
     //cartago.invoke_obj("java.lang.System",currentTimeMillis,T);
     //println(T);
