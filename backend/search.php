<?php
    $conn = mysqli_connect("localhost","root","","freshfood");
    if(isset($_GET['key'])){
        $search = $_GET['key'];
        $sql = "SELECT * FROM sanpham where tenhang like '%$search%'";
    }else{
        $sql = "SELECT * FROM sanpham";
    }
    $kq = mysqli_query($conn,$sql);
    $product = array();
    while($row = mysqli_fetch_assoc($kq)){
        $temp = array();
        $temp['id'] = $row['id'];
        $temp['tenhang'] = $row['tenhang'];
        $temp['image'] = $row['image'];
        $temp['dongia'] = $row['dongia'];
        $temp['mota'] = $row['mota'];
        
        array_push($product,$temp);
    }
    echo json_encode($product);
?>
