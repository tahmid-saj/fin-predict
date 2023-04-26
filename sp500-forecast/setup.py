import setuptools

long_description = "SP500_Forecast"

with open("requirements.txt", "r") as requirements_file:
    external_packages = requirements_file.read()

setuptools.setup(
    name="SP500_Forecast",
    version="0.0.1",
    author="tahmid-saj",
    description="Repo containing time series forecasting solutions ranging from N-BEATS, LSTM and autoregressive models trained for predicting stock and bitcoin price.",
    long_description=long_description,
    long_description_content_type="text/markdown",
    url="",
    project_urls={
        "Bug Tracker": "",
    },
    classifiers=[
        "Programming Language :: Python :: 3",
        "Operating System :: OS Independent",
    ],
    install_requires = external_packages,
    package_dir={"":"src"},
    packages=setuptools.find_namespace_packages(where="src\\"),
    include_package_data=True,
    python_requires=">=3.7",
)