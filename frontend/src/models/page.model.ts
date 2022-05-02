export interface Page<T> {
  content: T[];
  last: boolean;
  [x: string | number | symbol]: unknown;
}
