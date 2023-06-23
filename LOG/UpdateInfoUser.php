<?php
// Kết nối đến cơ sở dữ liệu MySQL
$conn = mysqli_connect("localhost", "root", "", "doan2");
mysqli_set_charset($conn, "utf8");
// Kiểm tra kết nối
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Lấy các giá trị từ ứng dụng Android Kotlin
$name = $_GET['name'];
$zipcode =$_GET['zipcode'];
$email = $_GET['email'];
$country = $_GET['country'];
$address = $_GET['address'];
$phone = $_GET['phone'];
$city =  $_GET['city'];
$id =  $_GET['id'];

// Thực hiện câu truy vấn cập nhật dữ liệu
$sql = "UPDATE tbl_customer SET name='$name', zipcode='$zipcode',country='$country', email='$email', address='$address', phone='$phone', city='$city' WHERE id='$id'";
$result = $conn->query($sql);

// Trả về kết quả dưới dạng JSON
header('Content-Type: application/json');
echo json_encode($response);

// Đóng kết nối
$conn->close();
?>
