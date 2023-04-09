<?php
    $conn = mysqli_connect("localhost","root","","freshfood");
    if(isset($_POST['id'])){ 
        $id_hoadon = $POST('id');
        $sql = "SELECT *,Min(item_hoa_don.id) FROM item_hoa_don INNER JOIN hoadon on hoadon.id=item_hoa_don.id_hoadon WHERE hoadon.id = '$id_hoadon'";
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
