!semaforo_java.

+!semaforo_java
  <- cartago.new_obj("Semaforo",[],Id);
     
     cartago.invoke_obj("java.lang.Class",forName("Semaforo"),Class);
     println(Class).
	 
	 //EXEMPLOS
	 //cartago.invoke_obj(Id,inc);
     //cartago.invoke_obj(Id,getValue,Res);
     //println(Res);
     //cartago.invoke_obj("java.lang.System",currentTimeMillis,T);
     //println(T);
