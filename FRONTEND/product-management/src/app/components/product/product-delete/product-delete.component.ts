import { Product } from './../../../model/product.model';
import { ProductService } from './../../../service/product.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface DialogData {
  id: string;
  name: string;
}

@Component({
  selector: 'app-product-delete',
  templateUrl: './product-delete.component.html',
  styleUrls: ['./product-delete.component.css']
})
export class ProductDeleteComponent implements OnInit {

  product: Product

  constructor(
    private productService: ProductService,
    public dialogRef: MatDialogRef<ProductDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {
    const id = this.data.id
    this.productService.readById(id).subscribe(product => {
      this.product = product
    })
  }

  onClick(): void{
    this.productService.delete(this.product).subscribe(product => {
      this.productService.showMessage(`Product ${product.name} deletado com sucesso`);
      this.dialogRef.close();
    })
  }

  onNoClick(): void{
    this.dialogRef.close();
  }

}
