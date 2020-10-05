import { FormControl, Validators } from '@angular/forms';
import { Product } from '../../../model/product.model';
import { ProductService } from '../../../service/product.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FUNCTION_TYPE, UPDATE_TITLE, SHOW_TITLE } from '../../../utils/constants';

@Component({
  selector: 'app-product-read-update',
  templateUrl: './product-read-update.component.html',
  styleUrls: ['./product-read-update.component.css']
})
export class ProductReadUpdateComponent implements OnInit {
  title: string = UPDATE_TITLE;
  product: Product;
  isDisabled: boolean = false;
  nameControl = new FormControl('', [Validators.required]);
  valueControl = new FormControl('', [Validators.required]);

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    const functionType = this.route.snapshot.paramMap.get('function').toUpperCase();
    this.changeScreenFields(functionType);
    this.getProductById();
  }

  updateProduct(): void {
    this.productService.update(this.product).subscribe(() => {
      this.productService.showMessage('Produto atualizado com sucesso!')
      this.router.navigate(['/products'])
    })
  }

  cancel(): void {
    this.router.navigate(['/products'])
  }

  changeScreenFields(functionType: string): void {
    if(functionType === FUNCTION_TYPE.READ){
      this.title = SHOW_TITLE;
      this.isDisabled = true;
    }
  }

  getProductById(): void{
    const id = this.route.snapshot.paramMap.get('id')
    this.productService.readById(id).subscribe(product => {
      this.product = product;
    });
  }

  isCreateDisabled(): boolean {
    return this.nameControl.invalid || this.valueControl.invalid;
  }

  getErrorMessage() {
    if (this.nameControl.hasError('required') || this.valueControl.hasError('required')) {
      return "Você deve preencher esse campo";
    }
  }

}
