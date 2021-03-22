export enum SectionType {
  primary = 'section_pr',
  secondary = 'section_se',
  thirtary = 'section_th'
}

export enum SectionBackground {
  image = 'image-hero_container',
  video = 'background_video'
}

interface ISectionMetaData {
  id: string;
  type: SectionType;
  background: SectionBackground;
}

interface ISectionTitle {
  title: string;
  subTitle: string;
  insideFirstColumn: boolean;
}

interface ISectionContent {
  column: string;
  text: string;
  component: string;
}

export interface IPageSection {
  metaData: ISectionMetaData;
  headerInfos: ISectionTitle;
  content: ISectionContent[];
  background: string;
}
