<?php
//koneksi Database 
$server = "localhost";
$username = "root";
$password = "";
$database = "doan2";
$conn = mysqli_connect($server, $username, $password, $database);


if (mysqli_connect_errno()) {
   echo "Không thể kết nối với Cơ sở dữ liệu" . mysqli_connect_errno();
}

$email = $_POST["post_email"];
$password = $_POST["post_password"];


// proses periksa username dan password di database 
$query = "SELECT * FROM tbl_customer where email='$email' AND password='$password' " ;
$obj_query = mysqli_query($conn, $query);
$data = mysqli_fetch_assoc($obj_query);


// periksa apakah Login sudah benar
if ($data) {
 echo json_encode(
   array(
     'response' => true,
     'payload' => array(
      "id" => $data["id"],
      "name" => $data["name"],
      "phone" => $data["phone"],
      "address" => $data["address"],
      "country" => $data["country"],
      "city" => $data["city"],
        "password" => $data["password"],
        "email" => $data["email"]
       
    )
  )
);
} else {
   echo json_encode(
     array(
      'response' => false,
      'payload' => null
    )
  );
}

header('content-Type: application/json');
?>