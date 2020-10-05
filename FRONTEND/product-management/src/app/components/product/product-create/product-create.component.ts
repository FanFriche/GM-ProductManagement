import { Router } from '@angular/router';
import { Product } from './../../../model/product.model';
import { ProductService } from './../../../service/product.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { CATEGORIES } from '../../../utils/constants';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  nameControl = new FormControl('', [Validators.required]);
  valueControl = new FormControl('', [Validators.required]);

  product: Product = {
    name: '',
    category: CATEGORIES.UNFORMATTED.PERISHABLE,
    value: null
  }

  constructor(
    private productService: ProductService,
    private router: Router,
  ) {}

  ngOnInit(): void {
  }

  isCreateDisabled(): boolean {
    return this.nameControl.invalid || this.valueControl.invalid;
  }

  createProduct(): void {
    this.productService.create(this.product).subscribe(() => {
      this.productService.showMessage('Produto criado com sucesso!')
      this.router.navigate(['/products'])
    })
  }

  cancel(): void{
    this.router.navigate(['/products'])
  }

  getErrorMessage() {
    if (this.nameControl.hasError('required') || this.valueControl.hasError('required')) {
      return "Você deve preencher esse campo";
    }
  }

}
