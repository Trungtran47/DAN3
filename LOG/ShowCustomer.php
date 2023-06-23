
<?php
// Kết nối đến cơ sở dữ liệu MySQL
$conn = mysqli_connect("localhost", "root", "", "doan2");
mysqli_set_charset($conn,"utf8");
// Kiểm tra kết nối
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$id =$_GET["id"];
// Truy vấn danh sách ảnh từ cơ sở dữ liệu
$sql = "select * from tbl_customer WHERE id= '$id'";
$result = $conn->query($sql);

// Tạo một mảng để lưu trữ các đường dẫn đến tệp ảnh
$customers = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $customer = array(
            "name" => $row["name"],
            "zipcode" => $row["zipcode"],
            "country" => $row["country"],
            "city" => $row["city"],
            "address" => $row["address"],
            "email" => $row["email"],
            "phone" => $row["phone"]
        );
        $customers[] = $customer;
    }
}


// Trả về danh sách ảnh dưới dạng JSON
header('Content-Type: application/json');
echo json_encode($customers);

// Đóng kết nối
$conn->close();
?>