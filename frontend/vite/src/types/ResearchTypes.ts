type Criteria = {
    inclusion: string[];
    exclusion: string[];
};

type StudyDuration = {
    start: string; // formatado como "yyyy-MM-dd"
    end: string;   // formatado como "yyyy-MM-dd"
};

export type Phase = {
    number: number;
    title: string;
    description: string;
};

type AttachmentFindResponseDTO = {
    name: string;
    archive: Base64URLString;
};

export type ResearchResponseDTO = {
    code: number;
    title: string;
    area: string;
    numberOfPatients: number;
    availableVacancies: number;
    responsibleDoctors: string[];
    institutions: string[];
    description: string;
    criteria: Criteria;
    studyDuration: StudyDuration;
    phases: Phase[];
    currentPhase: number;
    location: string;
    attachments: AttachmentFindResponseDTO[];
    patientsNamesAnyCode: string[];
    clinicalRepresentativeNameAndKey: string;
};

export type ResearchUpdateDTO = {
    title:string
    area:string
    numberOfPatients:number
    availableVacancies:number
    responsibleDoctors:string[]
    institutions:string[]
    description:string
    criteria:Criteria
    studyDuration:StudyDuration
    phases:Phase[]
    currentPhase:number
    location:string
    attachments:AttachmentFindResponseDTO[]
}

