import {
  AfterViewChecked,
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';

@Component({
  selector: 'app-tags-input',
  templateUrl: './tags-input.component.html',
})
export class TagsInputComponent implements AfterViewChecked, OnInit {
  @ViewChild('tagsContainer') tagsContainer!: ElementRef;
  @Output() tagsEvent = new EventEmitter<string[]>();
  @Input() initialTags!: string[];

  tags: string[] = [];
  text: string = '';
  leftPadding: number = 0;

  ngOnInit(): void {
    this.tags = this.initialTags ?? [];
  }

  ngAfterViewChecked() {
    let totalWidth = 0;

    for (const element of this.tagsContainer.nativeElement.children)
      totalWidth += element.clientWidth + 10;

    this.leftPadding = totalWidth + 10;
  }

  onKeyUp({ key, target }: KeyboardEvent) {
    const { value } = target as HTMLInputElement;
    const notBlank = value.trim() !== '';

    if (key === 'Enter' && notBlank) {
      this.tags.push(value);
      this.text = '';

      this.tagsEvent.emit(this.tags);
      return;
    }

    if (key === ',' || (key === ' ' && notBlank)) {
      this.tags.push(value.substring(0, value.length - 1));
      this.text = '';

      this.tagsEvent.emit(this.tags);
      return;
    }
  }

  onKeyDown({ key, target }: KeyboardEvent) {
    const { value } = target as HTMLInputElement;
    const isBlank = value.trim() === '';

    if (key === 'Backspace' && isBlank) {
      this.tags.pop();
      this.tagsEvent.emit(this.tags);
    }
  }
}
