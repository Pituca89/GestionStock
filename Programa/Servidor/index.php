<?php 
//header("Access-Control-Allow-Origin: *");
//header("Content-Type: application/json; charset=UTF-8");

require_once( 'mybdd.php' );

/**Recupero todo el stock actual */
if( isset( $_POST['data'] ) && $_POST['data'] == 'all' ) {
  
  $query = "CALL SCH_KIOSCO.SP_STOCK_ACTUAL('');";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Recupero todo el stock actual */
if( isset( $_POST['data'] ) && $_POST['data'] == 'cierrep' ) {
  $datos = json_decode($_POST["cod"]);
  $query = "CALL SCH_KIOSCO.CAJA_PARCIAL('".$datos->inicio."','".$datos->fin."',".$datos->idmaquina.");";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Recupero los productos en estado de reposicion */
if( isset( $_POST['data'] ) && $_POST['data'] == 'repo' ) {
  
  $query = "CALL SCH_KIOSCO.EN_ESTADO_REPOSICION();";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Recupero un conjunto de stock en particular */
if( isset( $_POST['data'] ) && $_POST['data'] == 'unstock') {
  
  $query = "CALL SCH_KIOSCO.SP_STOCK_ACTUAL('".$_POST['cod']."');";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Recupero un producto en particular */
if( isset( $_POST['data'] ) && $_POST['data'] == 'buscarprod') {
  
  $query = "CALL SCH_KIOSCO.BUSCAR_PRODUCTO('".$_POST['cod']."');";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Actualizo el stock */
if( isset( $_POST['data'] ) && $_POST['data'] == 'actualizar' && $_POST["cod"] != "" ) {
  
  $datos = json_decode($_POST["cod"]);
  
  $query = "CALL SCH_KIOSCO.ACTUALIZAR_STOCK('".$datos[0]->CODIGO_BARRAS."',"
  .$datos[0]->STOCK_MINIMO.","
  .$datos[0]->STOCK_ACTUAL.","
  .$datos[0]->USUARIO_ID.");";
  
  if(!$result = mysqli_query($conn, $query)) {
    echo "error";
    die(); //si la conexión cancelar programa  
  }else {
    echo "ok"; 
  }
}
/**Creo nueva venta */
if( isset( $_POST['data'] ) && $_POST['data'] == 'nueva_venta' && $_POST["cod"] != "") {
   
  $query = "CALL SCH_KIOSCO.NUEVA_VENTA('".$_POST["cod"]."');";
  
  if(!$result = mysqli_query($conn, $query)) {
    echo "error";
    die(); //si la conexión cancelar programa
  }
  $rawdata = array(); //creamos un array
  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Cargo los items de la venta */
if( isset( $_POST['data'] ) && $_POST['data'] == 'nuevo_item' && $_POST["cod"] != "" ) {
  
  
  $query = "CALL SCH_KIOSCO.NUEVO_ITEM('".$_POST["cod"]."');";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array
  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Registrar venta */
if( isset( $_POST['data'] ) && $_POST['data'] == 'venta' && $_POST["cod"] != "" ) {
  
  $datos = json_decode($_POST["cod"]);
  $cantidad = count($datos);
  for($i = 0; $i < $cantidad; $i++){

    $query = "CALL SCH_KIOSCO.INSERTAR_VENTA("
    .$datos[$i]->VENTA_ID.",'"
    .$datos[$i]->CODIGO_BARRAS."',"
    .$datos[$i]->SUBTOTAL.","
    .$datos[$i]->IS_FACTURABLE.","
    .$datos[$i]->CANTIDAD."); ";

    if(!$result = mysqli_multi_query($conn, $query)) {
      echo "error";
      die(); //si la conexión cancelar programa  
    }else {
      do {
        if ($result = $conn->store_result()) {  
          while ($myrow = $result->fetch_array(MYSQLI_ASSOC)){
            if($myrow["SALIDA"] == 1){
              echo "stock"; 
            }else{
              echo "ok";
            }
          }          
          $result->free();
        } else {
            if ($conn->errno) {
                echo  "error";
            }
        }
      } while ($conn->more_results() && $conn->next_result());     
    }
  }
}

/**Registrar gasto */
if( isset( $_POST['data'] ) && $_POST['data'] == 'gasto' && $_POST["cod"] != "" ) {
  
  $datos = json_decode($_POST["cod"]);
  $cantidad = count($datos);
  for($i = 0; $i < $cantidad; $i++){
    
    $query = "CALL SCH_KIOSCO.INSERTAR_GASTO('"
    .$datos[$i]->CODIGO_BARRA."',"
    .$datos[$i]->SUBTOTAL.","
    .$datos[$i]->CANTIDAD.");";

    if(!$result = mysqli_multi_query($conn, $query)) {
      echo "error";
      die(); //si la conexión cancelar programa  
    }else {
      do {
        if ($result = $conn->store_result()) {  
          while ($myrow = $result->fetch_array(MYSQLI_ASSOC)){
            if($myrow["SALIDA"] == 1){
              echo "stock"; 
            }else{
              echo "ok";
            }
          }          
          $result->free();
        } else {
            if ($conn->errno) {
                echo  "error";
            }
        }
      } while ($conn->more_results() && $conn->next_result());     
    }
  }
}


/**Registrar gasto */
if( isset( $_POST['data'] ) && $_POST['data'] == 'proveedor' && $_POST["cod"] != "" ) {
  
  $datos = json_decode($_POST["cod"]);
  $cantidad = count($datos);
  for($i = 0; $i < $cantidad; $i++){

    $query = "CALL SCH_KIOSCO.INSERTAR_GASTO_PROVEEDOR('"
    .$datos[$i]->CODIGO_BARRA."',"
    .$datos[$i]->SUBTOTAL.","
    .$datos[$i]->CANTIDAD.","
    .$datos[$i]->RUBRO.");";

    if(!$result = mysqli_query($conn, $query)) {
      echo "error";
      die(); //si la conexión cancelar programa  
    }else {
      echo "ok"; 
    }

  }
}
/**Registrar Producto */
if( isset( $_POST['data'] ) && $_POST['data'] == 'nuevo_producto' && $_POST["cod"] != "" ) {
  
  $datos = json_decode($_POST["cod"]);
  $cantidad = count($datos);
  for($i = 0; $i < $cantidad; $i++){
//[{"CATEGORY_ID":"MASALIN","IS_TEMPORAL":0,"CODIGO_BARRAS":"77917591","NOMBRE":"BAISHA ROJO","STOCK_MINIMO":200,"STOCK_ACTUAL":0,"VALOR_COSTO":73.0,"VALOR_VENTA":73.0,"USER":1,"PRODUCT_ID":2}]
    $query = "CALL SCH_KIOSCO.INSERTAR_PRODUCTO('"
    .$datos[$i]->CATEGORY_ID."',"
    .$datos[$i]->IS_TEMPORAL.",'"
    .$datos[$i]->CODIGO_BARRAS."','"
    .$datos[$i]->NOMBRE."',"
    .$datos[$i]->STOCK_MINIMO.","
    .$datos[$i]->STOCK_ACTUAL.","
    .$datos[$i]->VALOR_COSTO.","
    .$datos[$i]->VALOR_VENTA.","
    .$datos[$i]->USER.");";

    if(!$result = mysqli_query($conn, $query)) {
      echo "error";
      die(); //si la conexión cancelar programa  
    }else {
      while ($myrow = $result->fetch_array(MYSQLI_ASSOC))
      {
        if($myrow["SALIDA"] == 0){
          echo "ok"; 
        }else{
          echo "duplicado";
        }
      }     
    }
  }
}

/**Actualizar Producto */
if( isset( $_POST['data'] ) && $_POST['data'] == 'actprod' && $_POST["cod"] != "" ) {
  
  $datos = json_decode($_POST["cod"]);
  $cantidad = count($datos);
  for($i = 0; $i < $cantidad; $i++){

    $query = "CALL SCH_KIOSCO.ACTUALIZAR_PRODUCTO("
    .$datos[$i]->PRODUCT_ID.",
    '".$datos[$i]->CATEGORY_ID."',
    ".$datos[$i]->IS_TEMPORAL.",
    '".$datos[$i]->CODIGO_BARRAS."',
    '".$datos[$i]->NOMBRE."',"
    .$datos[$i]->STOCK_MINIMO.","
    .$datos[$i]->STOCK_ACTUAL.","
    .$datos[$i]->VALOR_COSTO.","
    .$datos[$i]->VALOR_VENTA.","
    .$datos[$i]->USER.");";

    if(!$result = mysqli_query($conn, $query)) {
      echo "error";
      die(); //si la conexión cancelar programa  
    }else {
      while ($myrow = $result->fetch_array(MYSQLI_ASSOC))
      {
        if($myrow["SALIDA"] == 0){
          echo "ok"; 
        }else{
          echo "duplicado";
        }
      }     
    }
  }
}
/**Cargo los items de la venta */
if( isset( $_POST['data'] ) && $_POST['data'] == 'eliminarprod' && $_POST["cod"] != "" ) {
  
  
  $query = "CALL SCH_KIOSCO.ELIMINAR_PRODUCTO(".$_POST["cod"].");";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  while ($myrow = $result->fetch_array(MYSQLI_ASSOC))
  {
    if($myrow["SALIDA"] == 1){
      echo "ok"; 
    }else{
      echo "error";
    }
  }  

}
/**Cargo los items de la venta */
if( isset( $_POST['data'] ) && $_POST['data'] == 'login' && $_POST["cod"] != "" ) {
  
  $datos = json_decode($_POST["cod"]);
  $query = "CALL SCH_KIOSCO.LOGIN('".$datos[0]->usuario."','".$datos[0]->password."');";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  while ($myrow = $result->fetch_array(MYSQLI_ASSOC))
  {
    if($myrow["SALIDA"] == 1){
      echo "ok"; 
    }else{
      echo "error";
    }
  }  

}

/**Cargo los items de la venta */
if( isset( $_POST['data'] ) && $_POST['data'] == 'iniciocaja' ) {
  
  
  $query = "CALL SCH_KIOSCO.INICIO_CAJA;";
  
  mysqli_query($conn, $query); //si la conexión cancelar programa

  echo "ok"; 
  
}

/**Cargo los items de la venta */
if( isset( $_POST['data'] ) && $_POST['data'] == 'cierrecaja' ) {
  
  
  $query = "CALL SCH_KIOSCO.CIERRE_CAJA;";
  
  mysqli_query($conn, $query); //si la conexión cancelar programa

  echo "ok"; 

}


/**Cargar lista de reposicion */
if( isset( $_POST['data'] ) && $_POST['data'] == 'allprod' ) {
  
  $datos = json_decode($_POST["cod"]);
  $query = "CALL SCH_KIOSCO.LISTA_DE_REPOSICION('".$datos[0]->NOMBRE."','".$datos[0]->RUBRO."');";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Cargar lista de reposicion */
if( isset( $_POST['data'] ) && $_POST['data'] == 'consulta_caja' ) {
  
  $query = "CALL SCH_KIOSCO.CONSULTA_CAJA();";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Cargar lista de reposicion */
if( isset( $_POST['data'] ) && $_POST['data'] == 'resumen' ) {
  
  $query = "CALL SCH_KIOSCO.RESUMEN_CAJA_FINAL();";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Cargar lista de reposicion */
if( isset( $_POST['data'] ) && $_POST['data'] == 'descargar_caja_dia' ) {
  
  $query = "CALL SCH_KIOSCO.DESCARGAR_CAJA_DIA();";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}
/**Cargar lista de reposicion */
if( isset( $_POST['data'] ) && $_POST['data'] == 'listaprod' ) {
  
  $datos = json_decode($_POST["cod"]);
  $query = "CALL SCH_KIOSCO.LISTA_DE_PRODUCTOS('".$datos[0]->NOMBRE."','".$datos[0]->RUBRO."');";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Cargar lista de reposicion */
if( isset( $_POST['data'] ) && $_POST['data'] == 'rubro' ) {
  
  $datos = json_decode($_POST["cod"]);
  $query = "CALL SCH_KIOSCO.CATEGORIAS();";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}

/**Cargar lista de reposicion */
if( isset( $_POST['data'] ) && $_POST['data'] == 'host' ) {
  
  $datos = json_decode($_POST["cod"]);
  $query = "CALL SCH_KIOSCO.HOST();";
  
  if(!$result = mysqli_query($conn, $query)) die(); //si la conexión cancelar programa

  $rawdata = array(); //creamos un array

  //guardamos en un array multidimensional todos los datos de la consulta
  $i=0;
  while($row = $result->fetch_object())
  {
      $rawdata[$i] = $row;
      $i++;
  }
  echo json_encode($rawdata); 
}
if( isset( $_GET['data'] ) && $_GET['data'] == '1' ) {
    $all_tutorials = 1;
     
    //$tutorials_data = get_posts( $all_tutorials );
    echo json_encode( $all_tutorials );
}
mysqli_close($conn);
?>

