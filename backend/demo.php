<?php
    $conn = mysqli_connect("localhost","root","","freshfood");
    if($_SERVER["REQUEST_METHOD"]=="POST"){
        $json = $_POST['json'];
        // $total = $_POST['tongtien'];
        $id_user = $_POST['id_user'];
        $data = json_decode($json,true);
        foreach($data as $value){
            $id_sanpham = $value['id_sanpham'];
            $tenhang = $value['tenhang'];
            $sl = $id_sanpham;
            $dg = $value['don_gia'];
            echo $id_sanpham."<br>";
            echo $tenhang."<br>";
            echo $sl."<br>";
            echo $dg."<br>";
            echo '----------------'."<br>";
        }
    }
?>