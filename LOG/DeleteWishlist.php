
<?php
// Kết nối đến cơ sở dữ liệu MySQL
$conn = mysqli_connect("localhost", "root", "", "doan2");
mysqli_set_charset($conn,"utf8");
// Kiểm tra kết nối
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
// Lấy giá trị sId từ yêu cầu (request)
$productId = $_GET["productId"];  

// Truy vấn danh sách ảnh từ cơ sở dữ liệu
$sql ="DELETE FROM tbl_wishlist WHERE productId ='$productId'";
$result = $conn->query($sql);

// Tạo một mảng để lưu trữ các đường dẫn đến tệp ảnh
$images = array();

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        // Mã hóa tên tệp ảnh bằng giải thuật băm MD5
        $filename = $row["image"] ;

        
        // Thêm đường dẫn đến tệp ảnh vào mảng
        $image = array(
            "productId" => $row["productId"],

            "image" => $filename 
        );
        array_push($images, $image);
    }
    
}

// Trả về danh sách ảnh dưới dạng JSON
header('Content-Type: application/json');
echo json_encode($images);

// Đóng kết nối
$conn->close();

?>