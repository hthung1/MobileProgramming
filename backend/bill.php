<?php
    $conn = mysqli_connect("localhost","root","","freshfood");
    if($_SERVER["REQUEST_METHOD"]=="POST"){
        $json = $_POST['json'];
        $total = $_POST['tongtien'];
        $gio = date("H:i");
        $ngay = date("Y-m-d");
        $id_user = $_POST['id_user'];
        $data = json_decode($json,true);
        $insertorder = "INSERT INTO hoadon(tongtien,iduser,ngay,gio) VALUES('$total',$id_user,$ngay,$gio)";
        mysqli_query($conn,$insertorder);
        $orderid = $conn->insert_id; 
        foreach($data as $value){
            $id_sanpham = $value['id_sanpham'];
            $tenhang = $value['tenhang'];
            $sl = $value['so_luong'];
            $dg = $value['don_gia'];
            $sql = "insert into item_hoa_don (id,id_hoadon,id_sanpham,tenhang,so_luong,don_gia)
                value(null,'$orderid','$id_sanpham','$tenhang','$sl','$dg')";
                mysqli_query($conn,$sql);
        }
    }
?>