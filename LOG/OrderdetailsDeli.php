<?php
// Kết nối đến cơ sở dữ liệu MySQL
$conn = mysqli_connect("localhost", "root", "", "doan2");
mysqli_set_charset($conn, "utf8");
// Kiểm tra kết nối
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Xử lý dữ liệu đầu vào
$customer_id = isset($_GET["customer_id"]) ? $_GET["customer_id"] : "";
$customer_id = mysqli_real_escape_string($conn, $customer_id);

// Truy vấn danh sách đơn đặt hàng từ cơ sở dữ liệu
$sql = "select * from tbl_order WHERE customer_id = '$customer_id' and status = '1'";
$result = $conn->query($sql);
// Tạo một mảng để lưu trữ các thông tin đơn đặt hàng
$orders = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        // Mã hóa tên tệp ảnh bằng giải thuật băm MD5
        $filename = $row["image"];

        // Thêm thông tin đơn đặt hàng vào mảng
        $order = array(
            "id" => $row["id"],
            "productName" => $row["productName"],
            "quantity" => $row["quantity"],
            "price" => $row["price"],
            "status" => $row["status"],
            "image" => $filename
        );
        array_push($orders, $order);
    }
}

// Trả về thông tin đơn đặt hàng dưới dạng JSON
header('Content-Type: application/json');
echo json_encode($orders);

// Đóng kết nối
$conn->close();
?>
