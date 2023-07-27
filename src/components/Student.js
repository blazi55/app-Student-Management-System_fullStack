import * as React from 'react';
import {useState} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {Button, Paper} from '@mui/material';

export default function Student() {
    const papierStyle = {padding:'50px 20px', width: 600, margin: "20px auto"}
    const textStyle = {margin: "10px"}
    const[name, setName] = useState('')
    const[address, setAddress] = useState('')
    const[student, setStudent] = useState([])
    const[studentId, setStudentId] = useState(0)
    const[studentName, setStudentName] = useState('')
    const[studentSingle, setStudentSingle] = useState([])
   

    const onSave = (e) => {
        e.preventDefault()
        const studentSave = {name, address}
        console.log(studentSave)
        fetch("http://localhost:8080/student", {
            method: "POST",
            headers: {
              'Content-Type': 'application/json',
              'Access-Control-Allow-Origin': 'http://localhost:8080/student'
            },
            body: JSON.stringify(studentSave)
        }).then(() => {
            console.log("Added")
        })
    }

    const onDelete = () => {
      fetch(`http://localhost:8080/student/${studentId}`, {
          method: "DELETE",
          headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': 'http://localhost:8080/student'
          },
      }).then(() => {
          console.log("Dead")
          console.log(studentId)
      })
  }

  const getStudent = async () => {
    const response = await fetch(`http://localhost:8080/student/studentName?name=${studentName}`,{
      method: "GET",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:8080/student'
      }
    }
    ).then((response) => response.json());
    console.log(response)
    setStudentSingle(response);
    console.log(response)
  };

    // useEffect(() => {
    //   fetch("http://localhost:8080/student/all", {
    //   headers: {
    //     'Content-Type': 'application/json',
    //     'Access-Control-Allow-Origin': 'http://localhost:8080/student'
    //   },
    //   method: "GET",
    // })
    //   .then(res => {
    //     return res.json();
    //   }).then(data => {
    //     setStudent(data);
    //   });
    // }/*mozna dodac []*/);

  return (
    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <Paper elevation={3} style={papierStyle}>
        <TextField id="outlined-basic" label="Student Name" variant="outlined" value={name} onChange={(e) => setName(e.target.value)}
        fullWidth style={textStyle}/>
        <TextField id="outlined-basic" label="Address" variant="outlined" value={address} onChange={(e) => setAddress(e.target.value)} 
        fullWidth style={textStyle}/>
        <Button variant="outlined" onClick={onSave}> Submit </Button>
        <div className='studentId'>
          Write Id to delete Student
          </div>
        <TextField id="outlined-basic" label="StudentId" variant="outlined" value={studentId} onChange={(e) => setStudentId(e.target.value)} 
            fullWidth style={textStyle}/>
        <Button variant="outlined" onClick={onDelete}> Delete Student </Button>
        <div className='studentId'>
          Write Name to get Student
          </div>
        <TextField id="outlined-basic" label="Student Name" variant="outlined" value={studentName} 
        onChange={(e) => setStudentName(e.target.value)} 
            fullWidth style={textStyle}/>
        <Button variant="outlined" onClick={getStudent}> Get Student </Button>
      </Paper>
      <Paper elevation={3} style={papierStyle}>
        <div>Students Data: </div>
        {studentSingle.map(st => (
          <Paper elevation={3} style={{margin:"10px", padding:"15px", textAlign:"center"}} key={st.id}>
            <div> <b>UserName:</b> {st.name}</div>
            <div>-------------------------</div>
            <div> <b>Adress:</b> {st.address}</div>
          </Paper>
        ))}
        
        {/* {student.map(st => (
          <Paper elevation={3} style={{margin:"10px", padding:"15px", textAlign:"center"}} key={st.id}>
            <div> <b>UserName:</b> {st.name}</div>
            <div>-------------------------</div>
            <div> <b>Adress:</b> {st.address}</div>
          </Paper>
        ))} */}
      </Paper>
    </Box>
  );
}
