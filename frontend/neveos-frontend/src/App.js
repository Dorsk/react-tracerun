import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'

import HeaderComponent from './components/HeaderComponent';
import TraceComponent from './components/home/TraceComponent';

function App() {
  return (
    <div>
      <Router>
        <HeaderComponent />
        <div >
          <Routes>
            <Route path="/Trace" exact element={<TraceComponent/>}></Route>
          </Routes>
        </div>
      </Router>
    </div >
  );
}

export default App;
