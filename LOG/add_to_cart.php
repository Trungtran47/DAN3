<?php
$conn = mysqli_connect("localhost", "root", "", "doan2");
mysqli_set_charset($conn,"utf8");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $productId = $_POST['productId'];
    $productName = $_POST["productName"];
    $price = $_POST["price"];
    $quantity = $_POST["quantity"];
    $imageUrl = $_POST['image'];
    $sId = $_POST['sId'];
 

    // Lấy tên tệp tin từ đường dẫn hình ảnh
    $image = basename($imageUrl);
    

    $query_insert = "INSERT INTO tbl_cart (productId, sId, productName, quantity, image ,price) VALUES ('$productId','$sId','$productName','$quantity','$image','$price')";
    $insert_cart = mysqli_query($conn, $query_insert);

    if ($insert_cart) {
        $response = array("status" => "success", "message" => "Added to cart successfully");
        echo json_encode($response);
    } else {
        $response = array("status" => "error", "message" => "Failed to add to cart");
        echo json_encode($response);
    }
}

mysqli_close($conn);
?>
