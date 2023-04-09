<?php
     $conn = mysqli_connect("localhost","root","","freshfood");
     if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $id = $_POST['id'];
        $name = $_POST['tenhang'];
        $image = $_POST['image'];
        $dg = $_POST['dongia'];
        $mt = $_POST['mota'];
        $sql = "update sanpham set tenhang = '$name',image = '$image',dongia = '$dg',mota = '$mt' where id = $id";
     }
     $kq = mysqli_query($conn,$sql);
     $product = array();
     $temp = array();
     $temp['id'] = $id;
     $temp['tenhang'] = $name;
     $temp['image'] = $image;
     $temp['dongia'] = $dg;
     $temp['mota'] = $mt;
     array_push($product,$temp);
     echo json_encode($temp);

     
?>