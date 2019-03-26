import { ElementUIComponent, ElementUIComponentSize } from './component'

export type TagType = 'primary' | 'gray' | 'success' | 'warning' | 'danger'

/** Tags Component */
export declare class ElTag extends ElementUIComponent {
  /** Theme */
  type: TagType

  /** Whether Tab can be removed */
  closable: boolean

  /** Whether the removal animation is disabled */
  disableTransitions: boolean

  /** Whether Tags has a highlighted border */
  hit: boolean

  /** Background color of the tag */
  color: string

  /** Tags size */
  size: ElementUIComponentSize
}
