document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("appointmentForm");

    const email = document.getElementById("email_Id");
    const contact = document.getElementById("contactNO");
    const location = document.getElementById("location");

    const doctor = document.getElementById("doctor");
    const service = document.getElementById("service");

    const appointmentDate =
        document.getElementById("appointmentDate");

    const appointmentTime =
        document.getElementById("appointmentTime");

    const reason = document.getElementById("reason");

    const createdAt =
        document.getElementById("createdAt");

    const appointmentId =
        document.getElementById("appointmentId");

    const reasonCount =
        document.getElementById("reasonCount");

    const formAlert =
        document.getElementById("formAlert");

    const submitBtn =
        document.getElementById("submitBtn");

    const clearBtn =
        document.getElementById("clearBtn");


    /* =====================================================
       SET MINIMUM DATE
    ===================================================== */

    function setMinimumDate() {

        const today = new Date();

        const year =
            today.getFullYear();

        const month =
            String(today.getMonth() + 1)
                .padStart(2, "0");

        const day =
            String(today.getDate())
                .padStart(2, "0");

        appointmentDate.min =
            `${year}-${month}-${day}`;
    }

    setMinimumDate();


    /* =====================================================
       CREATED AT
    ===================================================== */

    function setCreatedAt() {

        const now = new Date();

        createdAt.value =
            now.toLocaleString("en-IN", {
                dateStyle: "medium",
                timeStyle: "short"
            });
    }

    setCreatedAt();


    /* =====================================================
       APPOINTMENT ID
       Frontend placeholder only.
       Actual ID should preferably be generated
       by Spring Boot.
    ===================================================== */

    function generateTemporaryId() {

        appointmentId.value =
            "TEMP-" +
            Date.now();

    }

    generateTemporaryId();


    /* =====================================================
       PHONE INPUT
    ===================================================== */

    contact.addEventListener("input", function () {

        this.value =
            this.value.replace(/\D/g, "");

        if (
            /^[6-9][0-9]{9}$/.test(this.value)
        ) {

            this.classList.remove("is-invalid");
            this.classList.add("is-valid");

        } else {

            this.classList.remove("is-valid");

        }

    });


    /* =====================================================
       EMAIL
    ===================================================== */

    email.addEventListener("input", function () {

        const pattern =
            /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (
            pattern.test(this.value.trim())
        ) {

            this.classList.remove("is-invalid");
            this.classList.add("is-valid");

        } else {

            this.classList.remove("is-valid");

        }

    });


    /* =====================================================
       LOCATION
    ===================================================== */

    location.addEventListener("input", function () {

        if (
            this.value.trim().length >= 3
        ) {

            this.classList.remove("is-invalid");
            this.classList.add("is-valid");

        } else {

            this.classList.remove("is-valid");

        }

    });


    /* =====================================================
       REASON COUNTER
    ===================================================== */

    reason.addEventListener("input", function () {

        reasonCount.textContent =
            this.value.length;

    });


    /* =====================================================
       DATE VALIDATION
    ===================================================== */

    appointmentDate.addEventListener(
        "change",
        function () {

            if (!this.value) {
                return;
            }

            const selected =
                new Date(
                    this.value + "T00:00:00"
                );

            const today =
                new Date();

            today.setHours(
                0, 0, 0, 0
            );


            if (selected < today) {

                this.setCustomValidity(
                    "Appointment date cannot be in the past."
                );

                this.classList.add(
                    "is-invalid"
                );

                this.classList.remove(
                    "is-valid"
                );

            } else {

                this.setCustomValidity("");

                this.classList.remove(
                    "is-invalid"
                );

                this.classList.add(
                    "is-valid"
                );

            }

        }
    );


    /* =====================================================
       TIME VALIDATION
    ===================================================== */

    appointmentTime.addEventListener(
        "change",
        function () {

            validateTime();

        }
    );


    appointmentDate.addEventListener(
        "change",
        function () {

            if (appointmentTime.value) {
                validateTime();
            }

        }
    );


    function validateTime() {

        if (
            !appointmentDate.value ||
            !appointmentTime.value
        ) {
            return false;
        }


        const today = new Date();

        const selectedDate =
            new Date(
                appointmentDate.value +
                "T00:00:00"
            );


        const isToday =
            selectedDate.toDateString() ===
            today.toDateString();


        if (isToday) {

            const hours =
                String(today.getHours())
                    .padStart(2, "0");

            const minutes =
                String(today.getMinutes())
                    .padStart(2, "0");

            const currentTime =
                `${hours}:${minutes}`;


            if (
                appointmentTime.value <=
                currentTime
            ) {

                appointmentTime.setCustomValidity(
                    "Please select a future time."
                );

                appointmentTime.classList.add(
                    "is-invalid"
                );

                appointmentTime.classList.remove(
                    "is-valid"
                );

                return false;
            }

        }


        appointmentTime.setCustomValidity("");

        appointmentTime.classList.remove(
            "is-invalid"
        );

        appointmentTime.classList.add(
            "is-valid"
        );

        return true;

    }


    /* =====================================================
       FORM SUBMIT
    ===================================================== */

    form.addEventListener(
        "submit",
        function (event) {

            event.preventDefault();


            let valid = true;


            /* ---------------------------------------------
               EMAIL
            --------------------------------------------- */

            const emailPattern =
                /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (
                !emailPattern.test(
                    email.value.trim()
                )
            ) {

                email.classList.add(
                    "is-invalid"
                );

                email.classList.remove(
                    "is-valid"
                );

                valid = false;

            } else {

                email.classList.remove(
                    "is-invalid"
                );

                email.classList.add(
                    "is-valid"
                );

            }


            /* ---------------------------------------------
               PHONE
            --------------------------------------------- */

            if (
                !/^[6-9][0-9]{9}$/.test(
                    contact.value
                )
            ) {

                contact.classList.add(
                    "is-invalid"
                );

                contact.classList.remove(
                    "is-valid"
                );

                valid = false;

            } else {

                contact.classList.remove(
                    "is-invalid"
                );

                contact.classList.add(
                    "is-valid"
                );

            }


            /* ---------------------------------------------
               LOCATION
            --------------------------------------------- */

            if (
                location.value.trim().length < 3
            ) {

                location.classList.add(
                    "is-invalid"
                );

                location.classList.remove(
                    "is-valid"
                );

                valid = false;

            } else {

                location.classList.remove(
                    "is-invalid"
                );

                location.classList.add(
                    "is-valid"
                );

            }


            /* ---------------------------------------------
               DOCTOR
            --------------------------------------------- */

            if (!doctor.value) {

                doctor.classList.add(
                    "is-invalid"
                );

                doctor.classList.remove(
                    "is-valid"
                );

                valid = false;

            } else {

                doctor.classList.remove(
                    "is-invalid"
                );

                doctor.classList.add(
                    "is-valid"
                );

            }


            /* ---------------------------------------------
               SERVICE
            --------------------------------------------- */

            if (!service.value) {

                service.classList.add(
                    "is-invalid"
                );

                service.classList.remove(
                    "is-valid"
                );

                valid = false;

            } else {

                service.classList.remove(
                    "is-invalid"
                );

                service.classList.add(
                    "is-valid"
                );

            }


            /* ---------------------------------------------
               DATE
            --------------------------------------------- */

            if (!appointmentDate.value) {

                appointmentDate.classList.add(
                    "is-invalid"
                );

                appointmentDate.classList.remove(
                    "is-valid"
                );

                valid = false;

            }


            /* ---------------------------------------------
               TIME
            --------------------------------------------- */

            if (
                !appointmentTime.value ||
                !validateTime()
            ) {

                appointmentTime.classList.add(
                    "is-invalid"
                );

                appointmentTime.classList.remove(
                    "is-valid"
                );

                valid = false;

            }


            /* ---------------------------------------------
               REASON
            --------------------------------------------- */

            const reasonText =
                reason.value.trim();


            if (
                reasonText.length < 10 ||
                reasonText.length > 500
            ) {

                reason.classList.add(
                    "is-invalid"
                );

                reason.classList.remove(
                    "is-valid"
                );

                valid = false;

            } else {

                reason.classList.remove(
                    "is-invalid"
                );

                reason.classList.add(
                    "is-valid"
                );

            }


            /* =================================================
               INVALID
            ================================================= */

            if (!valid) {

                formAlert.className =
                    "alert alert-danger";

                formAlert.innerHTML =
                    '<i class="bi bi-exclamation-circle me-2"></i>' +
                    '<strong>Please review the form.</strong> ' +
                    'Some information is missing or invalid.';

                formAlert.classList.remove(
                    "d-none"
                );


                formAlert.scrollIntoView({
                    behavior: "smooth",
                    block: "center"
                });

                return;

            }


            /* =================================================
               SUCCESS
            ================================================= */

            formAlert.className =
                "alert alert-success";

            formAlert.innerHTML =
                '<i class="bi bi-check-circle me-2"></i>' +
                '<strong>Details verified.</strong> ' +
                'Submitting your appointment request...';

            formAlert.classList.remove(
                "d-none"
            );


            /* Disable submit */

            submitBtn.disabled = true;

            submitBtn.innerHTML =
                '<span class="spinner-border spinner-border-sm"></span>' +
                ' Submitting...';


            /* Submit to Spring Boot */

            setTimeout(function () {

                form.submit();

            }, 700);

        }
    );


    /* =====================================================
       CLEAR FORM
    ===================================================== */

    clearBtn.addEventListener(
        "click",
        function () {

            setTimeout(function () {

                formAlert.className =
                    "alert d-none";

                formAlert.innerHTML = "";


                const fields =
                    form.querySelectorAll(
                        ".is-valid, .is-invalid"
                    );


                fields.forEach(function (field) {

                    field.classList.remove(
                        "is-valid",
                        "is-invalid"
                    );

                });


                reasonCount.textContent =
                    "0";


                setCreatedAt();

                generateTemporaryId();


                submitBtn.disabled =
                    false;

                submitBtn.innerHTML =
                    'Request Appointment' +
                    '<i class="bi bi-arrow-right"></i>';


            }, 0);

        }
    );

});