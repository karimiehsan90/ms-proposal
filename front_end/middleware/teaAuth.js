export default function ({ store, redirect }) {
    // If the user is not authenticated
    if (!store.state.auth) {
        return redirect('/login')
    }
    if (store.state.auth.role!=='TEACHER' && store.state.auth.role!=='MANAGER'){
        return redirect('/')
    }
}
