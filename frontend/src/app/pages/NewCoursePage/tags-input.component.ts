import {
  AfterViewChecked,
  Component,
  ElementRef,
  ViewChild,
} from '@angular/core';

@Component({
  selector: 'app-tags-input',
  templateUrl: './tags-input.component.html',
})
export class TagsInputComponent implements AfterViewChecked {
  @ViewChild('tagsContainer') tagsContainer!: ElementRef;

  tags: string[] = [];
  text: string = '';
  leftPadding: number = 0;

  ngAfterViewChecked() {
    let totalWidth = 0;

    for (const element of this.tagsContainer.nativeElement.children)
      totalWidth += element.clientWidth + 10;

    this.leftPadding = totalWidth + 10;

    console.log(this.leftPadding);
  }

  onKeyUp({ key, target }: KeyboardEvent) {
    const { value } = target as HTMLInputElement;
    const notBlank = value.trim() !== '';

    if (key === 'Enter' && notBlank) {
      this.tags.push(value);
      this.text = '';
      return;
    }

    if (key === ',' || (key === ' ' && notBlank)) {
      this.tags.push(value.substring(0, value.length - 1));
      this.text = '';
      return;
    }
  }

  onKeyDown({ key, target }: KeyboardEvent) {
    const { value } = target as HTMLInputElement;
    const isBlank = value.trim() === '';

    if (key === 'Backspace' && isBlank) {
      this.tags.pop();
    }
  }
}
