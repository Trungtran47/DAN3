<?php
$conn = mysqli_connect("localhost", "root", "", "doan2");
mysqli_set_charset($conn, "utf8");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $product_id = $_POST['product_id'];
    $tenbinhluan = $_POST['customerName'];
    $binhluan = $_POST['comment'];
    // Lấy tên tệp tin từ đường dẫn hình ảnh
   

    $query_insert = "INSERT INTO tbl_binhluan(tenbinhluan, binhluan, product_id) VALUES ('$tenbinhluan','$binhluan','$product_id' )";

    $insert_cart = mysqli_query($conn, $query_insert);

    if ($insert_cart) {
        $response = array("status" => "success", "message" => "Added to comment successfully");
        echo json_encode($response);
    } else {
        $response = array("status" => "error", "message" => "Failed to add to comment");
        echo json_encode($response);
    }
}

mysqli_close($conn);
?>
