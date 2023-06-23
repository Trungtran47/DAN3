<?php
// Kết nối đến cơ sở dữ liệu MySQL
$conn = mysqli_connect("localhost", "root", "", "doan2");
mysqli_set_charset($conn, "utf8");

// Kiểm tra kết nối
if (mysqli_connect_errno()) {
    die("Connection failed: " . mysqli_connect_error());
}

// Lấy giá trị sId từ yêu cầu (request)
$productName = isset($_POST["productName"]) ? mysqli_real_escape_string($conn, $_POST["productName"]) : '';

// Truy vấn danh sách sản phẩm từ cơ sở dữ liệu
$sql = "SELECT * FROM tbl_product WHERE productName LIKE '%$productName%'";
$stmt = mysqli_prepare($conn, $sql);
mysqli_stmt_execute($stmt);

$result = mysqli_stmt_get_result($stmt);

// Tạo một mảng để lưu trữ các sản phẩm
$products = array();

if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        // Mã hóa tên tệp ảnh bằng giải thuật băm MD5
        $filename = $row["image"];

        // Thêm sản phẩm vào mảng
        $product = array(
            "productId" => intval($row["productId"]),
            "productName" => $row["productName"],
            "product_desc"=> str_replace(';', '',strip_tags(html_entity_decode($row["product_desc"]))),
            "price" => $row["price"],
            "image" => $filename
        );
        array_push($products, $product);
    }
}

// Trả về danh sách sản phẩm dưới dạng JSON
header('Content-Type: application/json');
echo json_encode($products);

// Đóng kết nối
mysqli_stmt_close($stmt);
mysqli_close($conn);
?>
