type MedicalHistory = {

}

export type  PatientRequestDTO = {
    name:string
    gender:string
    email:string
    birth:string //yyyy-mm-dd
    phone:string
    password:string
    roles:string
    medicalHistory:MedicalHistory
    doctorKey:string
} 

export type PatientResponse = {
    name: string;
    email: string;
    password: string; 
    roles: "PATIENT" | "DOCTOR" | "STUDY_REPRESENTATIVE"; 
    code: string;
    gender: "Male" | "Female" | "Other"; 
    birth: [number, number, number]; 
    digitalSignatureConsent: boolean; 
    responsibleDoctor: boolean; 
    authorizations: string[];
    researches: any[]; 
    notifications: any[]; 
    signature: string | null;
    doctorName: string; 
    doctorCRM: string; 
    medicalHistory:string
  }

  export type MedicalHistoryRequestDTO = {
    patientCode:string
    message:string
  }