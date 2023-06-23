<?php
$server = "localhost";
$username = "root";
$password = "";
$database = "doan2";
$conn = mysqli_connect($server, $username, $password, $database);

$name  =   mysqli_real_escape_string($conn, $_POST['name']);
$zipcode =  mysqli_real_escape_string($conn, $_POST['zipcode']);
$country = mysqli_real_escape_string($conn, $_POST['country']);
$city  =   mysqli_real_escape_string($conn, $_POST['city']);
$address =  mysqli_real_escape_string($conn, $_POST['address']);
$email = mysqli_real_escape_string($conn, $_POST['email']);
$phone  =   mysqli_real_escape_string($conn, $_POST['phone']);
$password =  mysqli_real_escape_string($conn, $_POST['password']);





$query_check = "SELECT * FROM `tbl_customer` WHERE `email` = '" . $email . "'";
$data_check = mysqli_query($conn, $query_check);
$numrow = mysqli_num_rows($data_check);

if ($numrow > 0) {
    $arr = [
        'success' => false,
        'message' => "Email đã được sử dụng"
    ];
} else {
    $query_insert = "INSERT INTO `tbl_customer`( `name`, `zipcode`, `country`, `city`, `address`, `email`, `phone`, `password`) 
                    VALUES ('" . $name . "','" . $zipcode . "','" . $country . "','" . $city . "','" . $address . "','" . $email . "','" . $phone . "','" . $password . "')";
    $data_insert = mysqli_query($conn, $query_insert);

    if ($data_insert) {
        $arr = [
            'success' => true,
            'message' => "Thành công",
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => "Thất bại",
        ];
    }
}
echo json_encode($arr);
?>
